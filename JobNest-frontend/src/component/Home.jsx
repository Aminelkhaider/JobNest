import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../css/HomePage.css';
import { isUserLoggedIn, logout, isAdminUser,getLoggedInUser } from '../service/AuthApiService'
const HomePage = () => {
  const user =getLoggedInUser()
    return (
      <div className="center-in-page">
      
        <Container>
          <Row className="justify-content-center align-items-center text-center">
            <Col lg={8}>
              <h1 className="display-4">Welcome to <span className="text-primary">JobNest</span> M.<span>{user}</span></h1>
              <p className="lead mb-4">
              Build a nest for your team's success and experience a smarter way to manage tasks and collaborate seamlessly.
              </p>
            </Col>
          </Row>
        </Container>
      </div>
    );
  };

export default HomePage;
