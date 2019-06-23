public class Planet {
    private static final double Gforce = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    //constructor
    public Planet(double xP, double yP, double xV, double yV, double m, String imgString) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = imgString;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        return Gforce * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
        return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] plist) {
        double netForce = 0;
        for(Planet p : plist) {
            if(this.equals(p)) {
                continue;
            }
            else {
            netForce += this.calcForceExertedByX(p);
            }
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Planet[] plist) {
        double netForce = 0;
        for(Planet p : plist) {
            if(this.equals(p)) {
                continue;
            }
            else {
            netForce += this.calcForceExertedByY(p);
            }
        }
        return netForce;
    }

    public void update(double dt, double fX, double fY) {
        double accX = fX / mass;
        double accY = fY / mass;

        xxVel += dt * accX;
        yyVel += dt * accY;

        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        //StdDraw.clear();
        StdDraw.picture(xxPos, yyPos, imgFileName);
        //StdDraw.show();
        //StdDraw.pause(1);
    }
}