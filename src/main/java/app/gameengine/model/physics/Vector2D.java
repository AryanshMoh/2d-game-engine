package app.gameengine.model.physics;

import java.util.Objects;

public class Vector2D {

    private double x;
    private double y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        } else if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Vector2D other = (Vector2D) obj;
        return Math.abs(this.x - other.x) < 1e-9 && Math.abs(this.y - other.y) < 1e-9;
    }
    @Override
    public int hashCode() {
        return Objects.hash(Math.round(x / 1e-9), Math.round(y / 1e-9));
    }

}
