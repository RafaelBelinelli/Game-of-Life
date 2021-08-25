public class Coordenada {
    private final int x;
    private final int y;

    public Coordenada(int x, int y) throws Exception {
        if (x < 0 || y < 0) {
            throw new Exception("Coordenada negativa");
        }

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", this.x + 1, this.y + 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Coordenada coordenada = (Coordenada) obj;

        if (this.x != coordenada.x) {
            return false;
        }

        if (this.y != coordenada.y) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int ret = 435;

        ret = ret * 13 + Integer.valueOf(this.x).hashCode();
        ret = ret * 13 + Integer.valueOf(this.y).hashCode();

        if (ret < 0) {
            ret = -ret;
        }

        return ret;
    }
}
