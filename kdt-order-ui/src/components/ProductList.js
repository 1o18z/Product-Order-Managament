import React, { useLayoutEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { Table } from 'react-bootstrap';

export function ProductList() {
    const [products, setProducts] = useState([]);

    useLayoutEffect(() => {
        axios.get('http://localhost:8080/api/products')
            .then((response) => {
                setProducts(response.data);
            });
    }, []);

    return (
        <>
            <h1>Product Table</h1>
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>id</th>
                    <th>productName</th>
                    <th>category</th>
                    <th>price</th>
                    <th>description</th>
                    <th>createdAt</th>
                </tr>
                </thead>
                <tbody>
                {products.map((product) => (
                    <tr key={product.productId}>
                        <td>
                            <Link to={`/products/${product.productId}`}>{product.productId}</Link>
                        </td>
                        <td>{product.productName}</td>
                        <td>{product.category}</td>
                        <td>{product.price}</td>
                        <td>{product.description}</td>
                        <td>{product.createdAt}</td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </>
    );
}
