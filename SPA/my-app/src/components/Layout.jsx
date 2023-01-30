import React from "react";
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import Books from "./BooksComponent";
import Header from "./HeaderComponent";
import Footer from "./FooterComponent";
import Rents from "./RentsComponent";
import Users from "./UsersComponent";
import AddRent from "./AddRent";
import AddBook from "./AddBook";
import AddUser from "./AddUser";
import Login from "./Login";
import Profile from "./Profile";

function Layout() {
    return (
        <BrowserRouter>
            <Header />
            <Routes>
                <Route path="/books" element={<Books />} />
                <Route path="/rents" element={<Rents />} />
                <Route path="/users" element={<Users />} />
                <Route path="/rents/add" element={<AddRent />} />
                <Route path="/books/add" element={<AddBook />} />
                <Route path="/users/add" element={<AddUser />} />
                <Route path="/login" element={<Login />} />
                <Route path="/profile" element={<Profile />} />

                <Route path="*" element={<Navigate
                    to="/books" replace />} />
            </Routes>
            <Footer />
        </BrowserRouter>
    );
}

export default Layout;