import HeaderComponent from "./component/HeaderComponent";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import CreateAccount from "./component/CreateAccount";
import LoginComponent from "./component/LoginComponent";
import { getLoggedInUserId, isUserLoggedIn, getLoggedInUser, isAdminUser } from "./service/AuthApiService";
import TasksComponent from "./component/TasksComponent";
import AddTaskComponent from "./component/AddTaskComponent";
import TaskHistory from "./component/TaskHistory";
import HomePage from "./component/Home";
import DetailPage from "./component/DetailPage";

import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Footer from "./component/Footer";

function App() {
  const activeUserId = getLoggedInUserId();
  const activeUser = getLoggedInUser();

  function AuthenticatedRoute({ children }) {
    const isAuthenticated = isUserLoggedIn();

    if (isAuthenticated) {
      return children;
    }
    return <Navigate to="/" />;
  }

  return (
    <>
      <BrowserRouter>
      <ToastContainer />
        <HeaderComponent />
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route
            path="/tasks"
            element={
              <AuthenticatedRoute>
                <TasksComponent userId={activeUserId} />
              </AuthenticatedRoute>
            }
          />
          {isAdminUser() &&<Route
            path="/add-task"
            element={
              <AuthenticatedRoute>
                <AddTaskComponent userId={activeUserId} />
              </AuthenticatedRoute>
            }
          />}

          <Route
           path="/task-details/:id" 
           element={
              <AuthenticatedRoute>
              <DetailPage userId={activeUserId}/> 
                </AuthenticatedRoute>
           }
              />

          {isAdminUser() && <Route
            path="/history"
            element={
              <AuthenticatedRoute>
                <TaskHistory userId={activeUserId} />
              </AuthenticatedRoute>
            }/>}
          
          {isAdminUser() && <Route
            path="/update-task/:id"
            element={
              <AuthenticatedRoute>
                <AddTaskComponent userId={activeUserId} />
              </AuthenticatedRoute>
            }
          />}
          <Route path="/create-account" element={<CreateAccount />} />
          <Route path="/login" element={<LoginComponent />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </>
  );
}

export default App;
