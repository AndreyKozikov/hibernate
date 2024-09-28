package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
/*
Задание: Настройте связь между вашим приложением и базой данных MySQL с использованием Hibernate.
Создайте несколько объектов Person и сохраните их в базу данных.

-- Создаем схему (базу данных)
CREATE DATABASE hibernate;

-- Выбираем созданную схему
USE hybernate;

-- Создаем таблицу Person
CREATE TABLE Person (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

 */

public class Main {
    public static void main(String[] args) {
        // Создание объекта Configuration и настройка свойств программно
        Configuration configuration = new Configuration();
        configuration.configure(); // Здесь можно явно не указывать XML, если он находится в корне проекта
        configuration.addAnnotatedClass(Person.class); // Добавляем класс сущности

        // Создание ServiceRegistry
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()) // Применение настроек конфигурации
                .build();

        // Создание SessionFactory с использованием ServiceRegistry
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        // Открытие сессии и выполнение CRUD-операций
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Person ivan = new Person("Ivan", 23);
            session.save(ivan);
            Person vasiliy = new Person("Vasiliy", 37);
            session.save(vasiliy);
        } finally {
            session.getTransaction().commit();
            sessionFactory.close();
        }
    }
}