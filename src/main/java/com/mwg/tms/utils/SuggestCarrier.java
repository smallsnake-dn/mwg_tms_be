package com.mwg.tms.utils;

import com.mwg.tms.services.impl.CarrierService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public void calculate() {
        Node n = new Node(new RoutePrice(), -1);
        long start = System.currentTimeMillis();

        buildTree(n, 0, resource);
        List<RoutePrice> ar = new ArrayList<>();
        solution(n,ar);

        long end = System.currentTimeMillis();
        listRs.forEach(g -> {
            System.out.print(g.price + " ");
        });
        System.out.println(" :" + rs);
        System.out.println((end - start) + "ms");

        System.out.println(n);
    }

    public void buildTree(Node root, int layer, HashMap<String, CarrierService.CarrierResource> rr) {
        List<RoutePrice> routePrices = data.get(layer);
        for (int i = 0; i < resource.keySet().size(); i++) {
            RoutePrice routePrice = routePrices.get(i);
            String keyMap = routePrice.getShippingPartner().getShippingpartnerid()
                    + routePrice.getRoute().getTypeofvehicleid().getTypeofvehicelid();
//            kiem tra tuyen co su dung loai phuong tien cua don vi do khong phu hop bang cach get price = -1
            if (rr.get(keyMap).getNumberofvehicle() > 0 && routePrice.getPrice() != -1) {
                Node n = new Node(routePrice, layer);
                root.child.add(n);
                // resource[i] = resource[i] - 1;
                // int _rr[] = Arrays.copyOf(rr, rr.length);
                // _rr[i] = _rr[i] - 1;
                if (layer < rows - 1) {
                    rr.get(keyMap).setNumberofvehicle(rr.get(keyMap).getNumberofvehicle() - 1);
                    // System.out.println("Layer:: " + layer);
                    buildTree(n, layer + 1, rr);
                    rr.get(keyMap).setNumberofvehicle(rr.get(keyMap).getNumberofvehicle() + 1);//rr[i] = rr[i] + 1;
                    // resource[i] = resource[i] + 1;
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
