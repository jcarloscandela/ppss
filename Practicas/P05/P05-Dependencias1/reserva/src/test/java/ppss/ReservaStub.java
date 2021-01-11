package ppss;

public class ReservaStub extends Reserva {
    private boolean permisos;
    @Override
    public boolean compruebaPermisos(String login, String password, Usuario usuario){
        return permisos;
    }
    public void setPermisos(boolean p) {
        permisos = p;
    }
}
