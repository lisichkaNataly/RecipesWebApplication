# RecipesWebApplication
приложение для сайта рецептов дополнения
Создан сервис, который хранит рецепты и возвращает рецепты по его идентификатору. 

Хранятся рецепты в карте в формате <номер рецепта, рецепт>. 

Поля класса рецепта содержат:

- название в формате строки;
- время приготовления в минутах в формате целого положительного числа;
- ингредиенты в формате списка объектов;
- шаги приготовления в формате списка строк.

Поля класса ингредиента содержат:

- название в формате строки;
- количество ингредиентов в формате целого положительного числа;
- единица измерения в формате строки.

 

В сервисе должны реализованы методы:

1. Добавление нового рецепта. 
    
    В метод передается заполненный объект класса рецепта, который сохраняется в карте и получает свой порядковый номер. 
    
2. Получение рецепта. 
    
    В метод передается порядковый номер рецепта, на выходе мы получаем из карты нужный объект. 
    

Создан такой же сервис, но для работы с ингредиентами:

- Хранятся ингредиенты в карте в формате <идентификатор, ингредиент>.
- В сервисе есть методы для добавления нового ингредиента и получения его по идентификатору. Можно делать по аналогии с сервисом рецептов.

Созданы контроллеры и методы для создания и получения рецептов и ингредиентов.

Исправлен код в соответствии с нормами REST:

- **Структура URL.** Path начинается с названия сущности, идентификатор - часть path. Остальные параметры — в строке URL. Создание и редактирование — через тело запроса.
- **Названия методов** отражают функциональность.
- **Названия параметров запросов**  короткие, понятные, отражают предназначение.
- **Разнесение эндпоинтов между контроллерами**: работа с рецептами отдельно от работы с ингредиентами.

В результате должно получиться приложение, в котором должно быть следующее (для каждого пункта должен быть соответствующий эндпоинт):

1. Добавление ингредиента.
2. Редактирование ингредиента по id.
3. Удаление ингредиента.
4. Получение информации об ингредиенте по id.
5. Получение полного списка ингредиентов.
6. Добавление рецепта.
7. Редактирование рецепта по id.
8. Удаление рецепта по id.
9. Получение рецепта по id.
10. Получение списка всех рецептов.


Подключены:  три библиотеки в проект:
- Lombok (удалено всё, кроме полей из POJO-классов, и поставлены нужные аннотации).
- Apache Commons (использованы утилитные методы из Apache Commons там, где это необходимо).
- Swagger (настроена генерацию UI с помощью Swagger, добавлено описание к контроллерам и эндпоинтам).


Настроено сохранение загруженных рецептов и добавленных ингредиентов в файлы.
Важно: сохранение загруженных рецептов и добавленных ингредиентов  происходит в разные файлы.
Требования к файлам:
Формат json;
Путь к файлу должен быть в application.properties;
Сохранение должно происходить при любом изменении рецептов и/или ингредиентов.
При запуске приложения данные нужно читать из файла с помощью метода с аннотацией @PostConstruct в сервисе.
Обработаны ошибки, которые могут возникнуть.
💡 Отладочная информация выводится в консоль 
e.printStackTrace()
В результате у вас должен получиться сервис, в котором есть два метода: сохранение данных в файл и чтение файла с диска. В сервисе с рецептами должен быть реализован метод с аннотацией @PostConstruct, который обращается к сервису с файлами, получает данные и записывает их в поле класса.

 - Созданы три эндпоинта.
Функционал и требования к ним:
 - Первый эндпоинт позволяет скачать все рецепты в виде json-файла.
 - Второй эндпоинт принимает json-файл с рецептами и заменяет сохраненный на жестком (локальном) диске файл на новый.
 - Третий эндпоинт принимает json-файл с ингредиентами и заменяет сохраненный на жестком (локальном) диске файл на новый.

Реализован отдельный контроллер, который работает с файлами
Созданы три метода в контроллере, которые реализуют работу с файлами в формате json
Обработаны ошибки в дописанном приложении


Дебаг. Обработаны исключительные ситуации. При возникновении исключений дописано приложение так, что клиенту будет отправляться HTTP-ответ с соответствующим кодом. 

- *Подсказка*
    
    Наиболее часто используемые статусы ответа:
    
    - 200 — всё хорошо, запрос выполнился.
    - 400 — есть ошибка в параметрах запроса.
    - 404 — URL неверный или такого действия нет в веб-приложении.
    - 500 — во время выполнения запроса произошла ошибка на сервере.

При обработке ошибок обращено внимание в первую очередь на:

1. Отсутствие данных по id; 
2. Ошибки при работе с потоками данных и файлами; 
3. Отсутствие рецептов или ингредиентов. 

В результате должно выйти полностью функционирующее приложение, в которое можно загружать рецепты, удалять их, менять, получать данные через HTTP-запросы. В приложении обработаны ошибки и исключения, при неверном вводе или выводе данных клиенту отправляется  HTTP-ответ с соответствующим кодом.
