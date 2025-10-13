package ru.nmt.client.di_client;

public interface ApiClient {

    <T> T post(String path, Object body, Class<T> responseType);
}
