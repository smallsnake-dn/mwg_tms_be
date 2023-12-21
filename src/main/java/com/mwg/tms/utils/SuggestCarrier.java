package com.mwg.tms.utils;

import com.mwg.tms.entities.Route;
import com.mwg.tms.repositories.IRouteRepository;
import com.mwg.tms.services.impl.CarrierService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

@Data
@AllArgsConstructor
public class SuggestCarrier {
    List<List<RoutePrice>> data;
    HashMap<String, CarrierService.CarrierResource> resource;


    List<RoutePrice> listRs;

    int rows;
    int cols;
    double rs;

    public SuggestCarrier(List<List<RoutePrice>> data, HashMap<String, CarrierService.CarrierResource> resource) {
        this.data = data;
        this.resource = resource;
        this.listRs = new ArrayList<>();
        this.rows = data.size();
        this.cols = resource.keySet().size();
        this.rs = -1;
    }

    @Data
    @AllArgsConstructor
    public class Node {
        public int layer;
        public RoutePrice data;
        public List<Node> child;

        public Node(RoutePrice data, int layer) {
            this.data = data;
            this.layer = layer;
            this.child = new ArrayList<>();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class RouteUsed {
        Instant startTime;
        Instant endtime;
        String typeVehicle;
    }

    public List<RoutePrice> calculate() {
        Node n = new Node(new RoutePrice(), -1);
        Stack<RouteUsed> internalResoure = new Stack<>();
        long start = System.currentTimeMillis();

        buildTree(n, 0, resource, internalResoure);
        List<RoutePrice> ar = new ArrayList<>();
        solution(n,ar);
        long end = System.currentTimeMillis();
        return listRs;
//        System.out.println(" :" + rs);
//        System.out.println((end - start) + "ms");
//
//        System.out.println(n);
    }

    private boolean checkResource() {
        return true;
    }

    public void buildTree(Node root, int layer, HashMap<String, CarrierService.CarrierResource> rr, Stack<RouteUsed> internalResoure) {
        List<RoutePrice> routePrices = data.get(layer);
        for (RoutePrice routePrice : routePrices) {
            String keyMap = routePrice.getShippingPartner().getShippingpartnerid()
                    + routePrice.getRoute().getTypeofvehicle().getTypeofvehicelid();
            if(routePrice.getShippingPartner().getShippingpartnerid().equals("1")) {
                int countR = 0;
                for(RouteUsed ru : internalResoure) {
                    Route route = routePrice.getRoute();
                    if(
                            (((ru.getStartTime().compareTo(route.getStarttime()) >= 0) && (ru.getStartTime().compareTo(route.getEndtime()) <= 0))
                            || ((ru.getEndtime().compareTo(route.getStarttime()) >= 0) && (ru.getEndtime().compareTo(route.getEndtime()) <= 0)))
                            && (ru.getTypeVehicle().equals(route.getTypeofvehicle().getTypeofvehicelid()))
                    ) {
                        countR = countR + 1;
                    }
                }
                if((rr.get(keyMap).getNumberofvehicle() - countR) > 0) {
                    Node n = new Node(routePrice, layer);
                    root.child.add(n);
                    if (layer < rows - 1) {
                        internalResoure.push(new RouteUsed(routePrice.getRoute().getStarttime(), routePrice.getRoute().getEndtime(),
                                routePrice.getRoute().getTypeofvehicle().getTypeofvehicelid()));
                        buildTree(n, layer + 1, rr, internalResoure);
                        internalResoure.pop();
                    }
                }
                break;
            }
            if (rr.get(keyMap).getNumberofvehicle() > 0 && routePrice.getPrice() != -1) {
                Node n = new Node(routePrice, layer);
                root.child.add(n);
                if (layer < rows - 1) {
                    rr.get(keyMap).setNumberofvehicle(rr.get(keyMap).getNumberofvehicle() - 1);
                    buildTree(n, layer + 1, rr, internalResoure);
                    rr.get(keyMap).setNumberofvehicle(rr.get(keyMap).getNumberofvehicle() + 1);//rr[i] = rr[i] + 1;
                }
            }
        }

        return;
    }

    public void solution(Node root, List<RoutePrice> ar) {
        ar.add(root.data);
        if (root.layer == rows - 1) {
            double rss = 0;
            for (int d = 0; d < ar.size(); d++) {
                rss = rss + ar.get(d).price;
                // System.out.print(ar.get(d) + " ");
            }
            if (rs == -1) {
                rs = rss;
                listRs = new ArrayList<>(ar);
            } else if (rs > rss) {
                rs = rss;
                listRs = new ArrayList<>(ar);
            }
            // System.out.println("rs:: " + rss);
        } else {
            root.child.forEach(n -> {
                if (n.child.size() >= 0) {
                    // System.out.println("n1:: " + n.data);
                    solution(n, ar);
                    // System.out.println("After: " + n.data);
                    ar.remove(ar.size() - 1);
                }
            });
        }
    }
}
