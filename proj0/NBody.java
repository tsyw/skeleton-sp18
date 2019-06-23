public class NBody {
    public static double readRadius(String file) {
        In input  = new In(file);
        int num = input.readInt();
        double universe = input.readDouble();

        return universe;
    }

    public static Planet[] readPlanets(String file) {
        In input = new In(file);
        int num = input.readInt();
        double universe = input.readDouble();
        Planet[] plist = new Planet[num];
        for(int i = 0; i != num; ++i) {
            double xP = input.readDouble();
            double yP = input.readDouble();
            double xV = input.readDouble();
            double yV = input.readDouble();
            double mass = input.readDouble();
            String name = "./images/" + input.readString();
            plist[i] = new Planet(xP, yP, xV, yV, mass, name);
        }
        return plist;
    }

    private static void drawBack(double universe, String back) {
        StdDraw.setScale(-universe, universe);
        StdDraw.clear();
        StdDraw.picture(0, 0, back);
        //StdDraw.show();
        //StdDraw.pause(2000);
    }

    private static void runSim(double T, double dt, Planet[] plist, double universe) {
        for(double time = 0; time != T; time += dt) {
            double[] xForces = new double[plist.length];
            double[] yForces = new double[plist.length];
            for(int i = 0; i != plist.length; ++i) {
                xForces[i] = plist[i].calcNetForceExertedByX(plist);
                yForces[i] = plist[i].calcNetForceExertedByY(plist);
            }
            drawBack(universe, "./images/starfield.jpg");
            for(int i = 0; i != plist.length; ++i) {
                plist[i].update(dt, xForces[i], yForces[i]);
                plist[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", plist.length);
        StdOut.printf("%.2e\n", universe);
        for (int i = 0; i < plist.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            plist[i].xxPos, plist[i].yyPos, plist[i].xxVel,
            plist[i].yyVel, plist[i].mass, plist[i].imgFileName);   
        }
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double universe = readRadius(filename);
        Planet[] plist = readPlanets(filename);

        StdDraw.enableDoubleBuffering();
        // Draw the background
        // drawBack(universe, "./images/starfield.jpg");

        // Draw the planets
        // for(Planet p : plist) {
        //     p.draw();
        // }

        // StdDraw.show();

        runSim(T, dt, plist, universe);

    }
}