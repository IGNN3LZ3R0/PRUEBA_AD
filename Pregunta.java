public class Pregunta {
    private String pregunta;
    private String[] opciones;
    private char respuestaCorrecta;

    public Pregunta(String pregunta, String opcion1, String opcion2, String opcion3, char respuestaCorrecta) {
        this.pregunta = pregunta;
        this.opciones = new String[]{opcion1, opcion2, opcion3};
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public char getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

   
    public String formatearPregunta() {
        StringBuilder sb = new StringBuilder();
        sb.append(pregunta).append("\n");
        for (String opcion : opciones) {
            sb.append(opcion).append("\n");
        }
        return sb.toString();
    }
}
