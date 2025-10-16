# 🧠 Mantık Açıklaması
## 🔹 handle(...)

Behavior zincirinin çalıştığı metot.
command veya query işlenmeden önce burada işlem yapılabilir.
`next.handle(...)` çağrıldığında zincirin bir sonraki halkasına geçilir (veya doğrudan handler çalıştırılır).

## 🔹 NextCommandHandler / NextQueryHandler
Zincirin bir sonraki halkasını temsil eder.
`next.handle(...)` çağrılmazsa işlem devam etmez __→__ bu sayede işlemi durdurabiliriz (örneğin yetkisiz erişim).

## 🧪 Örnek: Logging Behavior

```java
public class LoggingCommandBehavior<TCommand extends Command, TResult>
        implements CommandBehavior<TCommand, TResult> {

    @Override
    public Result<TResult> handle(TCommand command, NextCommandHandler<TCommand, TResult> next) {
        System.out.println("Command started: " + command.getClass().getSimpleName());
        Result<TResult> result = next.handle(command);
        System.out.println("Command finished: " + command.getClass().getSimpleName());
        return result;
    }
}
```

## 🧩 SOLID Uyumu

1. __Single Responsibility ->__ 	Handler sadece iş mantığına odaklanır, logging ayrı yapılır.
2. __Open/Closed ->__ Yeni behavior’lar eklenebilir, mevcut kod bozulmaz.
3. __Liskov Substitution ->__ Her behavior, ICommandBehavior gibi davranabilir.
4. __Interface Segregation ->__ ICommand ve IQuery behavior’ları ayrı interface’lerde.
5. __Dependency Inversion ->__ Üst modüller behavior’lara değil, sadece Mediator’a bağımlı.


## 🔄 Mediator ile Entegrasyon (Sonraki Adım)
Bir sonraki adımda bu behavior zincirini Mediator’a entegre edeceğiz. Böylece:

Mediator.send(command) dediğimizde:

Logging → Validation → Authorization → Handler sırasıyla çalışacak.
