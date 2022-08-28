package com.example.mappings.mappings.service;

import com.example.mappings.mappings.configuration.UserConfiguration;
import com.example.mappings.mappings.entities.Books;
import com.example.mappings.mappings.entities.Orders;
import com.example.mappings.mappings.entities.Users;
import com.example.mappings.mappings.enums.BookStatus;
import com.example.mappings.mappings.enums.OrderStatus;
import com.example.mappings.mappings.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    UserConfiguration userConfiguration;

    /**
     *
     * place an order
     *          ------>
     *
     *          Order (book / any inventory item)-->
     *
     *          create order in pending state
     *                  -- decrease quantity of the inventory item
     *                  -- deduct money from wallet
     *                  -- if money deduction is success
     *                      --- mark order to success.
     *
     *
     * Order Service
     *  - user placing an order
     *      1) Validate user ie check if user exists
     *      2) Validate book ie check if book is available
     *      3) Add a check that a user can only buy 10 books of same kind (by  that - same isbn)
     *      4) Create an order with pending state
     *      5) mark the book -- from available to sold off
     *      6) update the order to success
     *
     */

    @Transactional(rollbackFor = Exception.class)
    public Orders placeOrder(String userEmail , String isbn){
        Users existingUserByEmail = userService.findExistingUserByEmail(userEmail);
        Books book = bookService.findBookByIsbn(isbn);
        if(!book.getBookStatus().isAvailable()){
            throw new RuntimeException();
        }
        List<Books> booksList = new ArrayList<>();
        booksList.add(book);

        Orders orders = Orders.builder()
                .amount(book.getAmount())
                .orderStatus(OrderStatus.PENDING)
                .booksList(booksList)
                .user(existingUserByEmail).build();

        try {
            saveOrUpdate(orders);
            bookService.issueBookAndPersist(booksList);
            markOrderSuccess(orders);
        } catch (Exception exception){
            // rollback
            bookService.makeBooksAvailable(booksList);
            // mark order failed
            markOrderFailed(orders);
            throw new RuntimeException();
        }
        return orders;
    }

    private void markOrderFailed(Orders orders) {
        orders.setOrderStatus(OrderStatus.FAILED);
        saveOrUpdate(orders);
    }



    private void markOrderSuccess(Orders orders) {
        orders.setOrderStatus(OrderStatus.SUCCESS);
        saveOrUpdate(orders);
    }


    public Orders saveOrUpdate(Orders orders){
        return orderRepository.save(orders);
    }



}
