import React, { useLayoutEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { Button, Form } from 'react-bootstrap';

export function Product() {
    const [product, setProduct] = useState({});
    const { productId } = useParams();

    useLayoutEffect(() => {
        axios.get(`http://localhost:8080/api/products/${productId}`)
            .then((response) => {
                setProduct(response.data);
            });
    }, [productId]);

    const submit = (event) => {
        event.preventDefault();
        const data = {
            productName: event.target.elements.productName.value,
            category: event.target.elements.category.value,
            description: event.target.elements.description.value,
        };
        axios.put(`http://localhost:8080/api/products/${productId}`, data)
            .then((response) => {
                setProduct(response.data);
            });
    };

    return (
        <Form onSubmit={submit}>
            <h1>Product Details</h1>
            <Form.Group className='mb-3' controlId='formBasicEmail'>
                <Form.Label>Product Id</Form.Label>
                <Form.Control type='text' value={product.productId} readOnly />
            </Form.Group>

            <Form.Group className='mb-3' controlId='formBasicEmail'>
                <Form.Label>Name</Form.Label>
                <Form.Control type='text' name='productName' defaultValue={product.productName} />
            </Form.Group>

            <Form.Group className='mb-3' controlId='formBasicPassword'>
                <Form.Label>Category</Form.Label>
                <Form.Control type='text' name='category' value={product.category} readOnly />
            </Form.Group>

            <Form.Group className='mb-3' controlId='formBasicPassword'>
                <Form.Label>Description</Form.Label>
                <Form.Control type='text' name='description' defaultValue={product.description} />
            </Form.Group>

            <Form.Group className='mb-3' controlId='formBasicPassword'>
                <Form.Label>CreatedAt</Form.Label>
                <Form.Control type='text' value={product.createdAt} readOnly />
            </Form.Group>

            <Button type='submit'>Submit</Button>
        </Form>
    );
}
