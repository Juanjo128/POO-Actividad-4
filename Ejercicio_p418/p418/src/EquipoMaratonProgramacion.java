class EquipoMaratonProgramacion {
    String nombreEquipo;
    String universidad;
    String lenguajeProgramacion;
    Programador[] programadores;
    int tamañoEquipo;

    EquipoMaratonProgramacion(String nombreEquipo, String universidad, String lenguajeProgramacion) {
        this.nombreEquipo = nombreEquipo;
        this.universidad = universidad;
        this.lenguajeProgramacion = lenguajeProgramacion;
        this.tamañoEquipo = 0;
        this.programadores = new Programador[3];
    }

    boolean estáLleno() {
        return tamañoEquipo == programadores.length;
    }

    void añadir(Programador programador) throws Exception {
        if (estáLleno()) {
            throw new Exception("El equipo está completo. No se pudo agregar programador.");
        }
        programadores[tamañoEquipo] = programador;
        tamañoEquipo++;
    }

    static void validarCampo(String campo) throws Exception {
        for (int j = 0; j < campo.length(); j++) {
            char c = campo.charAt(j);
            if (Character.isDigit(c)) {
                throw new Exception("El campo '" + campo + "' no puede tener dígitos.");
            }
        }
        if (campo.length() > 20) {
            throw new Exception("La longitud de '" + campo + "' no debe ser superior a 20 caracteres.");
        }
    }
}
