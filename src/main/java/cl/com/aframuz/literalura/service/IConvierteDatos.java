package cl.com.aframuz.literalura.service;

public interface IConvierteDatos {

    <T> T getDatos(String json, Class<T> classe);

}

