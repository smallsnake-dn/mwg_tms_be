package com.mwg.tms.services.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mwg.tms.DTO.CarrierRequestDto;
import com.mwg.tms.DTO.CarrierRequestFilterDto;
import com.mwg.tms.DTO.CarrierUpdateRequestDto;
import com.mwg.tms.DTO.DriverInfo;
import com.mwg.tms.DTO.SuggestCarrierResponeDto;
import com.mwg.tms.DTO.UpdateStatusDto;
import com.mwg.tms.DTO.VehicleInfo;
import com.mwg.tms.entities.CarRentalInfomation;
import com.mwg.tms.entities.ChoiceOfTransportationPartner;
import com.mwg.tms.entities.Driver;
import com.mwg.tms.entities.Route;
import com.mwg.tms.entities.ShippingPartner;
import com.mwg.tms.entities.Vehicle;
import com.mwg.tms.repositories.ICOTPRepository;
import com.mwg.tms.repositories.ICarRentalInfomationRepository;
import com.mwg.tms.repositories.IDriverRepository;
import com.mwg.tms.repositories.IRouteRepository;
import com.mwg.tms.repositories.IShippingPartnerRepository;
import com.mwg.tms.repositories.IVehicleRepository;
import com.mwg.tms.services.ICarrierService;
import com.mwg.tms.services.IRouteService;
import com.mwg.tms.utils.RouteCalculator;

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

    @Override
    public List<Route> suggestCarrier(List<String> listRouteId) {
        // lấy đơn vị vận chuyển theo tuyến
        // lấy bảng giá theo km hoặc kg
        List<RouteCalculator> list = new ArrayList<>();

        // List<Route> list = routeService.getListRouteById(listRouteId);
        //
        // SuggestCarrierResponeDto suggest = new SuggestCarrierResponeDto("routeId",
        // "carrierId", 1.2f);
        // TODO Auto-generated method stub
        // List<SuggestCarrierResponeDto> list = new ArrayList<>();
        // list.add(suggest);
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
            choiceOfTransportationPartner.setVehicleid(vehicle.getVehilceid());
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
        CarRentalInfomation carRentalInfomation = carRentalInfomationRepository.findByRouteid(update.getRouteId());
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
