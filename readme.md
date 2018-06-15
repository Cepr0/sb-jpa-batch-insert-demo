Example of batch inserting in Spring Data JPA application

All you need is:

1) set the option `spring.jpa.properties.hibernate.jdbc.batch_size` to value you need.
2) use `saveAll()` method of your repo with the list of entities prepared for inserting.

Run this application and see the log:

```
2018-06-16 00:23:14.698  INFO 9128 --- [           main] jdbc.sqlonly                             : batching 5 statements: 1: insert into application$model (name, id) values ('model0', '<byte[]>') 
2: insert into application$model (name, id) values ('model1', '<byte[]>') 3: insert into application$model 
(name, id) values ('model2', '<byte[]>') 4: insert into application$model (name, id) values 
('model3', '<byte[]>') 5: insert into application$model (name, id) values ('model4', '<byte[]>') 

2018-06-16 00:23:14.705  INFO 9128 --- [           main] jdbc.sqlonly                             : batching 5 statements: 1: insert into application$model (name, id) values ('model5', '<byte[]>') 
2: insert into application$model (name, id) values ('model6', '<byte[]>') 3: insert into application$model 
(name, id) values ('model7', '<byte[]>') 4: insert into application$model (name, id) values 
('model8', '<byte[]>') 5: insert into application$model (name, id) values ('model9', '<byte[]>') 
```