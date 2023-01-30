import React from "react";
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { AddUserForm } from "./AddUserForm"
import axios from "axios";

function AddUser() {
    const navigate = useNavigate();
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [personalId, setPersonalId] = useState('');
    const [age, setAge] = useState('');
    const [errorMessage, setErrorMessage] = useState();

    const handleSubmit = (event) => {
        const formData = {
            firstName,
            lastName,
            login,
            password,
            personalId,
            age
        }

        axios
        .post(`/users/client`, formData)
        .then((res) => {
            navigate(`/users`);
        })
        .catch((err) => {
            setErrorMessage(err.response.data);
          });
    };

    return (
        <div className="container flex center-column">
            <AddUserForm
                handleSubmit={handleSubmit}
                setFirstName={setFirstName}
                setLastName={setLastName}
                setLogin={setLogin}
                setPassword={setPassword}
                setPersonalId={setPersonalId}
                setAge={setAge}
            />
        </div>
    )
}

export default AddUser;