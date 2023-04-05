# shortener
Укорачиватель ссылок Shortener (аналог укорачивателя
ссылок Google URL Shortener (https://goo.gl). 
Shortener - это класс, который может для любой строки вернуть некий
уникальный идентификатор и наоборот, по ранее полученному идентификатору
вернуть строку.

## Два дополнительных требования к Shortener:
- для двух одинаковых строк должен возвращаться один и тот же идентификатор;
- он должен поддерживать столько строк, сколько значений может принимать long,
именно этот тип будет использоваться для идентификатора.

## Поддерживает 6 стратегий хранения данных:
[HashMapStorageStrategy](src/project/strategy/HashMapStorageStrategy.java)
Хранилище на HashMap

[OurHashMapStorageStrategy](src/project/strategy/OurHashMapStorageStrategy.java)
Хранилище на HashMap, реализованной вручную

[FileStorageStrategy](src/project/strategy/FileStorageStrategy.java)
Хранилище на OurHashMap, в которой buckets представлены файлами

### Описанные далее стратегии ускорят получение идентификатора для строки
[OurHashBiMapStorageStrategy](src/project/strategy/OurHashBiMapStorageStrategy.java)
Хранилище двух HashMap(ключ -> значение и значение -> ключ)

[HashBiMapStorageStrategy](src/project/strategy/HashBiMapStorageStrategy.java)
Хранилище на HashMap, работающей в две стороны(Google Guava)

[DualHashBidiMapStorageStrategy](src/project/strategy/DualHashBidiMapStorageStrategy.java)
Хранилище на HashMap, работающей в две стороны(Apache Commons Collections)

Тестирование различных коллекций производилось как вручную, так и с помощью Junit тестов, которые проверяли правильность(FunctionalTest.java) и скорость работы(SpeedTest.java) класса.

