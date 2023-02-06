package com.example.astarproject;

import java.io.File;
import java.util.*;

public class PathFinder {
    public static File towns = new File("src/main/resources/com/example/astarproject/town.CSV");
    public static File roads = new File("src/main/resources/com/example/astarproject/Roads.CSV");
    private int counter = 0;
    private static HashMap<String, Node> table = new HashMap<>();
    private static City[] cities = new City[66];
    private static HashMap<String, City> citiesHash = new HashMap<>();

    public void Compute(City source, City targetCity) {
        table.clear();
        for (City i : cities) {
            if (i == null)
                continue;
            table.put(i.getName(), new Node(i, false, Double.POSITIVE_INFINITY, null));
        }
        QueComp comp = new QueComp();
        Queue<Node> q = new PriorityQueue<>(20, comp);
        Node node = table.get(source.getName());
        node.setDistance(0.0);
        node.setKnown(true);
        q.add(node);

        while (!q.isEmpty()) {
            Node temp = q.poll();
            temp.setKnown(true);
            if (temp.getCurrent() == targetCity) {
                break;
            }
            ArrayList<Neighbor> adj = temp.getCurrent().getAdjacent();
            for (Neighbor c : adj) {
                Node t = table.get(c.getDist().getName());
                if (t.isKnown()) {
                    continue;
                }
                double newDis = c.getRoad() + temp.getDistance();
                if (newDis < t.getDistance()) {
                    t.setSource(temp.getCurrent());
                    t.setDistance(newDis);
                }
                q.add(t);
            }
        }
    }

    public City getCity(String name) {
        return citiesHash.get(name);
    }

    public Node getNode(String name) {
        return table.get(name);
    }

    public boolean readCities(File f) {
        try {
            Scanner in = new Scanner(f);
            int cc = 62;
            int ac = 300;
            for (int i = 0; i < (cc); i++) {
                String[] city = in.nextLine().split(",");
                double x = Double.parseDouble(city[1]);
                double y = Double.parseDouble(city[2]);
                City temp = new City(x, y, city[0]);
                cities[counter++] = temp;
                Controller c = new Controller();
                c.CityCreator(x, y, city[0]);
            }
            for (int i = 0; i < cc; i++) {
                citiesHash.put(cities[i].getName(), cities[i]);
            }
            // f = new FileChooser().showOpenDialog(GUI.mainStage);
            f = roads;
            in.reset();
            in = new Scanner(f);
            for (int i = 0; i < ac; i++) {
                String[] link;
                try {
                    link = in.nextLine().split(" ");
                } catch (NoSuchElementException e) {
                    break;
                }
                City source = citiesHash.get(link[0]);
                City dist = citiesHash.get(link[1]);
                if (source == null || dist == null)
                    continue;
                double road = Double.parseDouble(link[2]);
                Neighbor temp = new Neighbor(source, dist, road);

                source.getAdjacent().add(temp);
                temp = new Neighbor(dist, source, road);
                dist.getAdjacent().add(temp);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
