import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export function OrderList() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:3000/orders')
        .then(response => {
          setOrders(response.data);
        })
        .catch(error => {
          console.error('Error fetching orders:', error);
        });
  }, []);

  return (
      <>
        <h1>Order Table</h1>
        <Table striped bordered hover>
          <thead>
          <tr>
            <th>id</th>
            <th>email</th>
            <th>address</th>
            <th>quantity</th>
            <th>orderStatus</th>
            <th>createdAt</th>
          </tr>
          </thead>
          <tbody>
          {orders.map(order => (
              <tr key={order.orderId}>
                <td><Link to={`/order/${order.orderId}`}>{order.orderId}</Link></td>
                <td>{order.email}</td>
                <td>{order.address}</td>
                <td>{order.quantity}</td>
                <td>{order.orderStatus}</td>
                <td>{order.createdAt}</td>
              </tr>
          ))}
          </tbody>
        </Table>
      </>
  );
}
