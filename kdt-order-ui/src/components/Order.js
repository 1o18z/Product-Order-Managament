import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { Button, Form } from 'react-bootstrap';

export function Order() {
    const [order, setOrder] = useState({});
    const { orderId } = useParams();

    useEffect(() => {
        axios.get(`http://localhost:3000/order/${orderId}`)
            .then(response => {
                console.log(response.data);
                setOrder(response.data);
            })
            .catch(error => {
                console.error('Error fetching order:', error);
            });
    }, [orderId]);

    const handleSubmit = () => {
        const data = {
            email: order.email,
            quantity: order.quantity,
            // Add any other required fields here based on your API requirements
        };

        axios.post(`http://localhost:3000/order/${orderId}`, data)
            .then(response => {
                console.log('Order submitted successfully:', response.data);
                setOrder(response.data);
            })
            .catch(error => {
                console.error('Error submitting order:', error);
            });
    };

    return (
        <Form>
            <h1>Product Details</h1>
            <Form.Group className='mb-3' controlId='formBasicName'>
                <Form.Label>Order Id</Form.Label>
                <Form.Control type='email' value={order.orderId} readOnly={true} />
            </Form.Group>

            {/* Other form groups for displaying order details */}

            <Button onClick={handleSubmit}>Submit</Button>
        </Form>
    );
}
