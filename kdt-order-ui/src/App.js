import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom';
import {Container} from 'react-bootstrap';
import {OrderList} from './components/OrderList';
import {Order} from './components/Order';
import {ProductList} from './components/ProductList';
import {Product} from './components/Product';

function App() {
    return (
        <Container>
            <Router>
                <Switch>
                    {/* Product Routes */}
                    <Route exact path='/products'>
                    </Route>
                    <Route exact path='/products/:productId'>
                    </Route>
                    <Route exact path='/products/:productId/update'>
                    </Route>

                    {/* Order Routes */}
                    <Route exact path='/orders'>
                    </Route>
                    <Route exact path='/orders/:orderId'>
                    </Route>
                    <Route exact path='/orders/:orderId/update'>
                    </Route>

                    <Route path='/'>
                        <Redirect to="/products"/>
                    </Route>
                </Switch>
            </Router>
        </Container>
    );
}

export default App;
