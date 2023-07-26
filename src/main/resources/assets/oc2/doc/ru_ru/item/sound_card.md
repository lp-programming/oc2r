# Звуковая карта
![Меньше звука тишины](item:oc2:sound_card)

Звуковая карта позволяет проигрывать разные звуковые эффекты из своей огромной библиотеки реалистичных семплов. Из-за некоторых технических особенностей, последовательное проигрывание эффектов требует небольшой паузы между ними. Если попытаться проиграть эффект в это время, то звука просто не будет.

Это высокоуровневое устройство, поэтому оно контролируется через высокоуровневое API (HLAPI). Дистрибутив Linux предоставляет Lua библиотеку для данного API. Например:  
`local d = require("devices")`  
`local s = d:find("sound")`  
`s:playSound("entity.creeper.primed")`

## API
Название устройства: `sound`

### Методы
`playSound(name:string[,volume:float,pitch:float])` проигрывает звуковой эффект с указанным именем.
- `name` - название эффекта для проигрывания.
- `volume` - громкость проигрывания эффекта в диапазоне от `0` до `1`, где `1` - нормальная громкость. Опционально, по умолчанию `1`.
- `pitch` - высота звука для проигрываемого эффекта в диапазоне от `0.5` до `2`, где `1` - нормальная высота. Опционально, по умолчанию `1`.
- Создает ошибку, если такого эффекта не существует.

`findSound(name:string):table` возвращает список доступных звуковых эффектов с таким именем. Учитывайте, что количество выдаваемых результатов ограничено, поэтому запросы с большим количеством результатов будут обрезаться.
- `name` - название звукового эффекта для поиска.
- Возвращает список названий звуковых эффектов по указанному запросу.