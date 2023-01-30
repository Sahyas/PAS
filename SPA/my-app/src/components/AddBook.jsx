import React from "react";
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { AddBookForm} from "./AddBookForm"
import axios from "axios";

function AddBook() {
    const navigate = useNavigate();
    const [title, setTitle] = useState('');
    const [author, setAuthor] = useState('');
    const [serialNumber, setSerialNuber] = useState('');
    const [genre, setGenre] = useState('');
    const [errorMessage, setErrorMessage] = useState();

    const handleSubmit = (event) => {
        const formData = {
            title,
            author,
            serialNumber,
            genre
        }

        axios
        .post(`/books`, formData)
        .then((res) => {
            navigate(`/books`);
        })
        .catch((err) => {
            setErrorMessage(err.response.data);
          });
    };

    return (
        <div className="container flex center-column">
            <AddBookForm
                handleSubmit={handleSubmit}
                setTitle={setTitle}
                setAuthor={setAuthor}
                setSerialNuber={setSerialNuber}
                setGenre={setGenre}
            />
        </div>
    )
}

export default AddBook;