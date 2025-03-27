# Задание 16. Класс сервис

* Задание 1: При добавлении сообщения реализовать проверку на наличие пользователя в БД, если пользователь с нужным id отсутствует в базе вернуть статус BAD_REQUEST 

* Задание 2: Реализовать удаление сообщения у пользователя по его id 

* Задание 3: Реализовать вывод списка сообщений и сообщения по id для конкретного пользователя

* Задание 4: Перенести логику из контроллера в класс сервиса.

* Классы должны располагаться следующим образом:

{корневой пакет}.controller.PersonController.java  
{корневой пакет}.repository.PersonRepository.java  
{корневой пакет}.dto.Person.java  
{корневой пакет}.controller.MessageController.java  
{корневой пакет}.repository.MessageRepository.java  
{корневой пакет}.dto.Message.java  
   
* Требования к API:

Person:

GET /person - Возврат списка объектов Person  
GET /person/{id} - Возврат объекта Person по id  
POST /person - Добавление объекта Person  
PUT /person/{id} - Изменение объекта Person по id  
DELETE /person/{id} - Удаление объекта Person по id  

GET /person/{p_id}/message - Возврат списка сообщений Message для объекта Person по p_id  
GET /person/{p_id}/message/{m_id} - Возврат сообщения Message с m_id для объекта Person по p_id  
POST /person/{p_id}/message - Добавление сообщения Message в объект Person с p_id  
DELETE /person/{p_id}/message/{m_id} - Удаление сообщения Message с m_id из объекта Person с p_id  
Поля объекта Person:

    int id;
    String firstname;
    String surname;
    String lastname;
    String birthday;
    List<Message> mesages;
 
Message:

GET /message - Возврат списка объектов Message  
GET /message/{id} - Возврат объекта Message по id  
POST /message - Добавление объекта Message  
PUT /message/{id} - Изменение объекта Message по id  
DELETE /message/{id} - Удаление объекта Message по id  
Поля объекта Message:

    int id;
    String title;
    String text;
    LocalDateTime time;
