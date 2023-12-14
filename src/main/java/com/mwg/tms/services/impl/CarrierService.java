package com.mwg.tms.services.impl;

import java.time.Instant;
import java.util.*;

import com.mwg.tms.entities.*;
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
import com.mwg.tms.repositories.ICOTPRepository;
import com.mwg.tms.repositories.ICarRentalInfomationRepository;
import com.mwg.tms.repositories.IDriverRepository;
import com.mwg.tms.repositories.IRouteRepository;
import com.mwg.tms.repositories.IShippingPartnerRepository;
import com.mwg.tms.repositories.IVehicleRepository;
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

    public CarrierService(IRouteRepository routeRepository, RouteService routeService,
                          ICarRentalInfomationRepository carRentalInfomationRepository, IDriverRepository driverRepository,
                          IVehicleRepository vehicleRepository, ICOTPRepository cotpRepository,
                          IShippingPartnerRepository shippingPartnerRepository) {
        this.routeRepository = routeRepository;
        this.routeService = routeService;
        this.carRentalInfomationRepository = carRentalInfomationRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.cotpRepository = cotpRepository;
        this.shippingPartnerRepository = shippingPartnerRepository;
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
        return CHI_PHI_KM + CHI_PHI_BOC_XEP + CHI_PHI_DIEM_DUNG;
    }

    private HashMap<String, ListRouteByLocation> calculate(List<Route> routes) {
        HashMap<String, ListRouteByLocation> routeByLocation = new HashMap<>();
//        ListRouteByLocation listRouteByLocation = null;
        for (Route r : routes) {
            ListRouteByLocation listRouteByLocation = null;
            listRouteByLocation = routeByLocation.get(r.getDeparturelocation().getLocationid());
            if (listRouteByLocation == null) {
                listRouteByLocation = new ListRouteByLocation();
            }
            routeByLocation.put(r.getDeparturelocation().getLocationid(), listRouteByLocation);
            List<ShippingPartner> shippingPartner = shippingPartnerRepository
                    .getShippingPartnerByLocation(r.getDeparturelocation(), r.getTypeofvehicleid());

            List<RoutePrice> routePrices = new ArrayList<>();
            for (ShippingPartner s : shippingPartner) {
//            add resource
                String keyMap = s.getShippingpartnerid() + r.getTypeofvehicleid().getTypeofvehicelid();
                if (listRouteByLocation.getResource().get(keyMap) == null) {
                    int numberOfVehicleBusy = routeRepository.getNumberOfVehicleBusy(s.getShippingpartnerid(),
                            r.getTypeofvehicleid().getTypeofvehicelid(), r.getDeparturelocation().getLocationid());

                    CarrierResource carrierResource =
                            new CarrierResource(s, r.getTypeofvehicleid(),
                                    s.getTransportationresource().get(0).getNumberofvehicle() - numberOfVehicleBusy);
                    listRouteByLocation.getResource().put(keyMap, carrierResource);
                }

//                tinh toan chi phi cua tuyen theo don vi van chuyen
                double price = calculatePriceForRoute(r, s);
                routePrices.add(new RoutePrice(s, r, price));
            }
            listRouteByLocation.listRouteWithCarrier.add(routePrices);


//            listRouteByLocation = new ListRouteByLocation();
//            List<CarrierResource> listCarrierResource = new ArrayList<>();
//            listRouteByLocation.setResource(listCarrierResource);
//            List<List<RoutePrice>> listRouteWithCarrier = new ArrayList<>();
//            // them gia cua tuyen theo don vi van chuyen
//            // listRouteWithCarrier.add
//            // chưa tính toán chi phí theo tuyến
//            listRouteByLocation.setListRouteWithCarrier(listRouteWithCarrier);
//
//            routeByLocation.put(r.getDeparturelocation().getLocationid(), listRoute);

        }
        ;
        return routeByLocation;
    }

    private void sugguest(HashMap<String, ListRouteByLocation> rs) {
        for(ListRouteByLocation l : rs.values()) {
            SuggestCarrier suggestCarrier = new SuggestCarrier(l.getListRouteWithCarrier(), l.getResource());
            suggestCarrier.calculate();
        }
    }

    @Override
    public List<Route> suggestCarrier(List<String> listRouteId) {
        // lấy đơn vị vận chuyển theo tuyến
        // lấy bảng giá theo km hoặc kg
        // List<RouteCalculator> list = new ArrayList<>();
        // listRouteId.forEach(id -> {
        // Route route = routeRepository.getRouteById(id);
        // String typeVehicle = route.getTypeofvehicleid().getTypeofvehicelid();
        // String startLocation = route.getDeparturelocation().getLocationid();

        // });

        List<Route> listRoute = routeRepository.findAllById(listRouteId);
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
            carRentalInfomationRepository.CreateCarRentalInformation((new Date()).toInstant(), 0, id);
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

    ChoiceOfTransportationPartner fetchChoiceOfTransportationPartner(String id) throws Exception {
        System.out.println("IDDDDDDDD: " + id);
        ChoiceOfTransportationPartner choiceOfTransportationPartner = cotpRepository.findCOTP(id);
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
        try {
            CarRentalInfomation carRentalInfomation = carRentalInfomationRepository
                    .findById(choiceOfTransportationPartner.getCarrentalinformationid()).get();
            if (carRentalInfomation == null) {
                throw new Exception("carRentalInfomation khong the tim thay");
            }
            carRentalInfomation.setStatus(2);
            carRentalInfomationRepository.save(carRentalInfomation);
        } catch (Exception e) {
            throw new Exception("Khong the UpdateRejectRental");
        }
    }

    void updateAcceptRental(ChoiceOfTransportationPartner choiceOfTransportationPartner) throws Exception {
        CarRentalInfomation carRentalInfomation = carRentalInfomationRepository
                .findById(choiceOfTransportationPartner.getCarrentalinformationid()).get();
        if (carRentalInfomation == null) {
            throw new Exception("carRentalInfomation khong the tim thay");
        }
        carRentalInfomation.setStatus(1);
        carRentalInfomationRepository.save(carRentalInfomation);
    }

    // void disableCOTP(Integer requestid) throws Exception {
    // ChoiceOfTransportationPartner c = cotpRepository.findCOTP(requestid);
    // c.setDeleteat((new Date()).toInstant());
    // cotpRepository.save(c);
    // }

    @Override
    public void updateStatus(UpdateStatusDto update) throws Exception {
        try {
            ChoiceOfTransportationPartner choiceOfTransportationPartner = fetchChoiceOfTransportationPartner(
                    update.getRequestid());

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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

    @Override
    public void updateCarrierForRoute(CarrierUpdateRequestDto update) throws Exception {
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

        ChoiceOfTransportationPartner choiceOfTransportationPartner2 = new ChoiceOfTransportationPartner();
        choiceOfTransportationPartner2.setCarrentalinformationid(carRentalInfomation.getCarrentalinformationid());
        choiceOfTransportationPartner2.setNote(update.getReason());
        choiceOfTransportationPartner2.setSenderinformation("Default");
        choiceOfTransportationPartner2.setShippingpartnerid(shippingPartner.getShippingpartnerid());
        cotpRepository.save(choiceOfTransportationPartner2);
    }

}
