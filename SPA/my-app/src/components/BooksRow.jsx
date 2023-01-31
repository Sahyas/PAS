import {
    TableRow,
    TableCell,
    Button,
} from "@mui/material";
import React, { useState } from "react";
import axios from "axios"

function BooksRow({item}) {
    const [rented, setRented] = React.useState(item.rented);
    const [errorMessage, setErrorMessage] = useState();

    const handleBookDelete = (id) => {
        axios
        .delete(`/books/${id}`)
        .then((res) => {
            window.location.reload();
        })
    .catch((err) => {
            setErrorMessage(err.response.data);
        })
    };

    return (
        <>
        <TableRow sx={{ "& > *": { borderBottom: "unset" } }}>
            <TableCell>{item.id}</TableCell>
            <TableCell>{item.title}</TableCell>
            <TableCell>{item.author}</TableCell>
            <TableCell>{item.serialNumber}</TableCell>
            <TableCell>{item.genre}</TableCell>
            <TableCell>{rented ? 'True' : 'False'}</TableCell>
            <TableCell>
            <Button onClick={() =>
                    handleBookDelete(item.id)}>
                    Delete Book
                </Button>
            </TableCell>
        </TableRow>
        </>
    );
}

export default BooksRow