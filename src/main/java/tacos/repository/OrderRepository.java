package tacos.repository;

import tacos.Order;

public interface OrderRepository {
    Order save(Order order);
}
