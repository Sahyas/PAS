import {
    TableRow,
    TableCell,
    Collapse,
    IconButton,
    Box,
    Typography,
    Table,
    TableBody,
    Button,
} from "@mui/material";
import React, { useState } from "react";
import axios from "axios"

function RentsRow({item}) {

    const [beginTime] = useState(new Date(item.beginTime).toJSON())
    const [endTime] = useState(new Date(item.endTime).toJSON())
    const [rents, setRents] = useState([]);
    const [errorMessage, setErrorMessage] = useState();


    console.log(item);

    const handleRentEnd = (id) => {
        axios
        .post(`/rents/return/${id}`)
        .then((res) => {
            window.location.reload();
        })
        .catch((err) => {
            setErrorMessage(err.response.data);
          });
    };

    const handleRentDelete = (id) => {
        axios
        .delete(`/rents/${id}`)
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
            <TableCell>{beginTime}</TableCell>
            <TableCell>{endTime}</TableCell>
            <TableCell>{item.book.title}</TableCell>
            <TableCell>{item.user.login}</TableCell>
            <TableCell>
                <Button onClick={() =>
                    handleRentEnd(item.book.id)}>
                    End Rent
                </Button>
            </TableCell>
            <TableCell>
                <Button onClick={() =>
                    handleRentDelete(item.id)}>
                    Delete Rent
                </Button>
            </TableCell>
        </TableRow>
        </>
    );
}

export default RentsRow;