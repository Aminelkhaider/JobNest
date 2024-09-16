import React from 'react'
import logo from '../assets/logo.svg'
import { NavLink, useNavigate } from "react-router-dom"
import { isUserLoggedIn, logout, isAdminUser,getLoggedInUser } from '../service/AuthApiService'

const HeaderComponent = () => {

    const isAuth = isUserLoggedIn()
    const navigate = useNavigate()
    const user =getLoggedInUser()

    function handleLogout() {
        logout()
        navigate('/login')
    }

    function isUrlHistory() {
        let url = window.location.href
        return url.endsWith("history")
    }
    //  Check if the URL ends with "history"


    return (
        <div>
            <nav className="fixed-top navbar navbar-expand-lg navbar-light">
                <div className="container">
                <NavLink className="navbar-brand" to="/">
                    <img src={logo} alt="logo" width={30} height={30} color='blue' />
                </NavLink>
                {/* <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon">hhhhh</span>
                </button> */}
                    {/* <a className="navbar-brand fw-bold">
                        <img src={logo} alt="logo" width={30} height={30} />
                    </a> */}
                    <ul className="navbar-nav gap-4">
                        {
                            isAuth &&
                            <li className="nav-item">
                                {
                                    !isUrlHistory() && isAdminUser() ?  <NavLink className='nav-link' to='/history'>Task History</NavLink> :  <NavLink className='nav-link' to='/tasks'>Tasks </NavLink>
                                }
                            </li>
                        }
                        {
                            !isAuth &&

                            <li className="nav-item">
                                <NavLink className='nav-link' to='/create-account'>Create account</NavLink>
                            </li>
                        }
                        {
                            !isAuth &&
                            <li className="nav-item">
                                <NavLink className='nav-link' to='/login'>Login</NavLink>
                            </li>
                        }
                        {
                            isAuth &&
                            <li className="nav-item">
                                <NavLink className='nav-link' to='/login' onClick={handleLogout}>Logout</NavLink>
                            </li>
                        }
                        {/* {isAuth &&
                            <li className="nav-item">
                                <NavLink className='nav-link' >{user.toUpperCase()}</NavLink>
                            </li>
                        } */}
                    </ul>
                </div>
            </nav>
        </div>
    )
}

export default HeaderComponent