package com.mwg.tms.services.impl;

import java.time.Instant;
import java.util.*;

import com.mwg.tms.entities.*;
import com.mwg.tms.repositories.*;
import com.mwg.tms.utils.RoutePrice;
import com.mwg.tms.utils.SuggestCarrier;
import org.springframework.stereotype.Service;

import com.mwg.tms.DTO.CarrierRequestDto;
import com.mwg.tms.DTO.CarrierRequestFilterDto;
import com.mwg.tms.DTO.CarrierUpdateRequestDto;
import com.mwg.tms.DTO.DriverInfo;
import com.mwg.tms.DTO.SuggestCarrierResponeDto;
import com.mwg.tms.DTO.UpdateStatusDto;
import com.mwg.tms.DTO.VehicleInfo;
import com.mwg.tms.services.ICarrierService;
import com.mwg.tms.services.IRouteService;
import com.mwg.tms.utils.RouteCalculator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
public class CarrierService implements ICarrierService {
    private IRouteRepository routeRepository;
    private IRouteService routeService;
    private ICarRentalInfomationRepository carRentalInfomationRepository;
    private IDriverRepository driverRepository;
    private IVehicleRepository vehicleRepository;
    private ICOTPRepository cotpRepository;
    private IShippingPartnerRepository shippingPartnerRepository;
    private ISuggestShippingUnitRepository suggestShippingUnitRepository;

    public CarrierService(IRouteRepository routeRepository, RouteService routeService,
                          ICarRentalInfomationRepository carRentalInfomationRepository, IDriverRepository driverRepository,
                          IVehicleRepository vehicleRepository, ICOTPRepository cotpRepository,
                          IShippingPartnerRepository shippingPartnerRepository, ISuggestShippingUnitRepository suggestShippingUnitRepository) {
        this.routeRepository = routeRepository;
        this.routeService = routeService;
        this.carRentalInfomationRepository = carRentalInfomationRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.cotpRepository = cotpRepository;
        this.shippingPartnerRepository = shippingPartnerRepository;
        this.suggestShippingUnitRepository = suggestShippingUnitRepository;
    }

    @Data
    @AllArgsConstructor
    public class CarrierResource {
        ShippingPartner shippingPartner;
        TypeOfVehicle typeOfVehicle;
        int numberofvehicle;
    }

//    @Data
//    @AllArgsConstructor
//    public class RoutePrice {
//        ShippingPartner shippingPartner;
//        Route route;
//        double price;
//    }

    @Data
    @AllArgsConstructor
    public class ListRouteByLocation {
        HashMap<String, CarrierResource> resource;
        List<List<RoutePrice>> listRouteWithCarrier;

        public ListRouteByLocation() {
            resource = new HashMap<>();
            listRouteWithCarrier = new ArrayList<>();
        }
    }

    double calculatePriceKM(Double length, List<CostLevelsPerKm> costLevelsPerKm) {
        return 1;
    }

    private double calculatePriceForRoute(Route route, ShippingPartner shippingPartner) {
        ShippingServicePrice shippingServicePrice = shippingPartner.getShippingserviceprice().get(0);
        double CHI_PHI_KM = calculatePriceKM(route.getRoutelength(), shippingServicePrice.getListcostlevelperkm());
        double CHI_PHI_BOC_XEP = shippingServicePrice.getLoadingfeecosts() * route.getListdeliverypoint().size();
        double CHI_PHI_DIEM_DUNG = shippingServicePrice.getStoppointcosts() * route.getListdeliverypoint().size();
        double price = CHI_PHI_KM + CHI_PHI_BOC_XEP + CHI_PHI_DIEM_DUNG;
        SuggestShippingUnitId id = new SuggestShippingUnitId();
        id.setRouteid(route.getRouteid());
        id.setShippingpartnerid(shippingPartner.getShippingpartnerid());
        SuggestShippingUnit suggestShippingUnit = new SuggestShippingUnit();
        suggestShippingUnit.setId(id);
        suggestShippingUnit.setRoute(route);
        suggestShippingUnit.setShippingpartner(shippingPartner);
        suggestShippingUnit.setCost(price);
        suggestShippingUnitRepository.save(suggestShippingUnit);
        return price;
    }

    private HashMap<String, ListRouteByLocation> calculate(List<Route> routes) {
        HashMap<String, ListRouteByLocation> routeByLocation = new HashMap<>();
//        ListRouteByLocation listRouteByLocation = null;
        for (Route r : routes) {
            ListRouteByLocation listRouteByLocation = null;
            listRouteByLocation = routeByLocation.get(r.getDeparturelocation().getLocationid());
            if (listRouteByLocation == null) {
                listRouteByLocation = new ListRouteByLocation();
                routeByLocation.put(r.getDeparturelocation().getLocationid(), listRouteByLocation);
            }
//            System.out.println("locationid: " + r.getDeparturelocation().getLocationid());
//            System.out.println("vehicleid: " + r.getTypeofvehicle().getTypeofvehicelid());
            List<ShippingPartner> shippingPartner = shippingPartnerRepository
                    .getShippingPartnerByLocation(r.getDeparturelocation(), r.getTypeofvehicle());
            List<RoutePrice> routePrices = new ArrayList<>();
            for (ShippingPartner s : shippingPartner) {
//            thêm tài nguyên
                String keyMap = s.getShippingpartnerid() + r.getTypeofvehicle().getTypeofvehicelid();
                if (listRouteByLocation.getResource().get(keyMap) == null) {
                    int numberOfVehicleBusy = routeRepository.getNumberOfVehicleBusy(s.getShippingpartnerid(),
                            r.getTypeofvehicle().getTypeofvehicelid(), r.getDeparturelocation().getLocationid());

                    CarrierResource carrierResource =
                            new CarrierResource(s, r.getTypeofvehicle(),
                                    s.getTransportationresource().get(0).getNumberofvehicle() - numberOfVehicleBusy);
                    listRouteByLocation.getResource().put(keyMap, carrierResource);
                }

                double price = calculatePriceForRoute(r, s);

                routePrices.add(new RoutePrice(s, r, price));
            }
            listRouteByLocation.listRouteWithCarrier.add(routePrices);
        }
        return routeByLocation;
    }

    private boolean sugguest(HashMap<String, ListRouteByLocation> rs) {
        for (ListRouteByLocation l : rs.values()) {
            SuggestCarrier suggestCarrier = new SuggestCarrier(l.getListRouteWithCarrier(), l.getResource());
            List<RoutePrice> listRoutePrice = suggestCarrier.calculate();
            for (int i = 1; i < listRoutePrice.size(); i++) {
                RoutePrice rp = listRoutePrice.get(i);

                CarRentalInfomation checkCarrental = carRentalInfomationRepository.findByRouteidAndStatusNull(rp.getRoute().getRouteid());
                if (checkCarrental != null) {
                    System.out.println("tuyen " + rp.getRoute().getRouteid() + " da co yeu cau thue");
                    continue;
                }

                CarRentalInfomation carRentalInfomation = new CarRentalInfomation();
                carRentalInfomation.setCarrentalinformationid(UUID.randomUUID().toString());
                carRentalInfomation.setRouteid(rp.getRoute().getRouteid());
                carRentalInfomationRepository.save(carRentalInfomation);

                ChoiceOfTransportationPartner choiceOfTransportationPartner = new ChoiceOfTransportationPartner();
                choiceOfTransportationPartner.setChoiceoftransportationpartnerid(UUID.randomUUID().toString());
                choiceOfTransportationPartner.setCarrentalinformationid(carRentalInfomation.getCarrentalinformationid());
                choiceOfTransportationPartner.setShippingPartner(rp.getShippingPartner());
                cotpRepository.save(choiceOfTransportationPartner);
            }
        }
        return true;
    }

    @Override
    public List<Route> suggestCarrier(List<String> listRouteId) throws Exception {
        List<Route> listRoute = routeRepository.findAllById(listRouteId);
        if (listRoute == null) {
            throw new Exception("suggest carrier khong the tim kiem danh sach tuyen");
        }
        HashMap<String, ListRouteByLocation> rs = calculate(listRoute);
        sugguest(rs);
        for (String s : rs.keySet()) {
            System.out.println("Key set: " + s);
        }
        return null;
    }

    @Override
    public void createShippingRequest(List<String> listIdRoute) throws Exception {
        listIdRoute.forEach(id -> {
            List<CarRentalInfomation> carRentalInfomation = carRentalInfomationRepository.findExistByRouteid(id);
            if (carRentalInfomation.isEmpty()) {
                carRentalInfomationRepository.CreateCarRentalInformation((new Date()).toInstant(), 0, id);
            }
        });
    }

    @Override
    public List<CarrierRequestDto> getListRequestByFilter(CarrierRequestFilterDto filter) {
        // TODO Auto-generated method stub
        return null;
    }

    List<Driver> fetchOrCreateDrivers(List<DriverInfo> driverInfos) throws Exception {
        try {
            List<Driver> drivers = new LinkedList<>();
            driverInfos.forEach(in4 -> {
                Driver driver = driverRepository
                        .findBycitizenIdentificationCard(in4.getCitizenIdentificationCard());
                if (driver == null) {
                    driver = new Driver();
                    driver.setDriverid(UUID.randomUUID().toString());
                    driver.setCitizenidentificationcard(in4.getCitizenIdentificationCard());
                    driver.setName(in4.getName());
                    driver.setPhone(in4.getPhoneNumber());
                    drivers.add(driver);
                }
            });
            driverRepository.saveAll(drivers);
            return drivers;
        } catch (Exception e) {
            throw new Exception("Khong the tim thay Driver: " + e.getMessage());
        }
    }

    Vehicle fetchOrCreateVehicle(String vehicleIn4) throws Exception {
        try {
            Vehicle vehicle = vehicleRepository.findByLicensePlate(vehicleIn4);
            if (vehicle == null) {
                vehicle = new Vehicle();
                vehicle.setVehilceid(UUID.randomUUID().toString());
                vehicle.setLicenseplate(vehicleIn4);
                vehicleRepository.save(vehicle);
            }
            return vehicle;
        } catch (Exception e) {
            throw new Exception("Khong the tim kiem Vehile: " + e.getMessage());
        }
    }

    ChoiceOfTransportationPartner fetchChoiceOfTransportationPartner(String routeid) throws Exception {
//        System.out.println("IDDDDDDDD: " + id);
        List<CarRentalInfomation> carRentalInfomation = carRentalInfomationRepository.findExistByRouteid(routeid);
        ChoiceOfTransportationPartner choiceOfTransportationPartner = cotpRepository.findCOTP(carRentalInfomation.get(0).getCarrentalinformationid());
        if (choiceOfTransportationPartner == null) {
            throw new Exception("Khong tim thay choiceOfTransportationPartner");
        }
        return choiceOfTransportationPartner;
    }

    void updateVehicleForCOTP(ChoiceOfTransportationPartner choiceOfTransportationPartner, Vehicle vehicle)
            throws Exception {
        try {
            choiceOfTransportationPartner.setVehicle(vehicle);
            cotpRepository.save(choiceOfTransportationPartner);
            return;
        } catch (Exception e) {
            throw new Exception("Khong the updateVehicleForCOTP");
        }
    }

    void updateDrivers(ChoiceOfTransportationPartner choiceOfTransportationPartner, List<Driver> drivers) {

    }

    // Cập nhật thông tin trạng thái
    // 0: chờ duyệt
    // 1: duyệt
    // 2: từ chối
    void updateRejectRental(ChoiceOfTransportationPartner choiceOfTransportationPartner) throws Exception {
        CarRentalInfomation carRentalInfomation = carRentalInfomationRepository
                .findById(choiceOfTransportationPartner.getCarrentalinformationid()).get();
        if (carRentalInfomation == null) {
            throw new Exception("carRentalInfomation khong the tim thay");
        }
        carRentalInfomation.setApproverinformation("default");
        carRentalInfomation.setApprovaldate((new Date()).toInstant());
        carRentalInfomation.setStatus(2);
        carRentalInfomationRepository.save(carRentalInfomation);
    }

    void updateAcceptRental(ChoiceOfTransportationPartner choiceOfTransportationPartner) throws Exception {
        CarRentalInfomation carRentalInfomation = carRentalInfomationRepository
                .findById(choiceOfTransportationPartner.getCarrentalinformationid()).get();
        if (carRentalInfomation == null) {
            throw new Exception("carRentalInfomation khong the tim thay");
        }
        carRentalInfomation.setApproverinformation("default");
        carRentalInfomation.setApprovaldate((new Date()).toInstant());
        carRentalInfomation.setStatus(1);
        carRentalInfomationRepository.save(carRentalInfomation);
    }

    @Override
    public void updateStatus(UpdateStatusDto.UpdateStatus update) throws Exception {
        ChoiceOfTransportationPartner choiceOfTransportationPartner = fetchChoiceOfTransportationPartner(
                update.getRouteid());

        // CHUA CAP NHAT THONG TIN NGUOI THUC HIEN CUA BEN DOI TAC
        if (!update.getType()) {
            updateRejectRental(choiceOfTransportationPartner);
            return;
        }

        List<Driver> drivers = fetchOrCreateDrivers(update.getDriverinfo());

        Vehicle vehicle = fetchOrCreateVehicle(update.getVehicleinfo());

        updateAcceptRental(choiceOfTransportationPartner);

        updateVehicleForCOTP(choiceOfTransportationPartner, vehicle);

        updateDrivers(choiceOfTransportationPartner, drivers);

        return;
    }

    @Override
    public void updateCarrierForRoute(CarrierUpdateRequestDto.CarrierUpdate update) throws Exception {
        CarRentalInfomation carRentalInfomation = carRentalInfomationRepository
                .findByRouteidAndStatusNull(update.getRouteId());
        if (carRentalInfomation == null) {
            throw new Exception("Khong the lay thong tin yeu cau CarRentalInfomation");
        }

        ChoiceOfTransportationPartner choiceOfTransportationPartner = cotpRepository
                .findCOTP(carRentalInfomation.getCarrentalinformationid());
        if (choiceOfTransportationPartner == null) {
            throw new Exception("Khong the lay thong tin yeu cau ChoiceOfTransportationPartner");
        }

        choiceOfTransportationPartner.setDeleteat((new Date()).toInstant());
        cotpRepository.save(choiceOfTransportationPartner);

        ShippingPartner shippingPartner = shippingPartnerRepository.findById(update.getCarrierId()).get();
        if (shippingPartner == null) {
            throw new Exception("Khong the lay thong tin yeu cau ShippingPartner");
        }

        Route r = routeRepository.getRouteById(update.getRouteId());

        calculatePriceForRoute(r, shippingPartner);

        ChoiceOfTransportationPartner choiceOfTransportationPartner2 = new ChoiceOfTransportationPartner();
        choiceOfTransportationPartner2.setChoiceoftransportationpartnerid(UUID.randomUUID().toString());
        choiceOfTransportationPartner2.setCarrentalinformationid(carRentalInfomation.getCarrentalinformationid());
        choiceOfTransportationPartner2.setNote(update.getReason());
        choiceOfTransportationPartner2.setSenderinformation("Default");
        choiceOfTransportationPartner2
                .setShippingPartner(shippingPartner);
        cotpRepository.save(choiceOfTransportationPartner2);
    }

}
