import React from "react";
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { AddRentForm } from "./AddRentForm";
import axios from "axios";

function AddRent() {
    const navigate = useNavigate();
    const [bookId, setBookId] = useState('');
    const [clientId, setClientId] = useState('');
    const [errorMessage, setErrorMessage] = useState();

    const handleSubmit = (event) => {
        axios
        .post(`/rents?clientId=${clientId}&bookId=${bookId}`)
        .then((res) => {
            navigate(`/added`);
        })
        .catch((err) => {
            setErrorMessage(err.response.data);
            navigate(`/error`)
          });
    };

    return (
        <div className="container flex center-column">
            <AddRentForm
                handleSubmit={handleSubmit}
                setBookId={setBookId}
                setClientId={setClientId}
            />
        </div>
    )
}

export default AddRent;