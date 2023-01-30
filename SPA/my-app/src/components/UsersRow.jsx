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

function UsersRow({item}) {
    const [active, setActive] = React.useState(item.active);
    const [errorMessage, setErrorMessage] = useState();

    const handleUserDelete = (id) => {
        axios
        .delete(`/users/${id}`)
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
            <TableCell>{item.login}</TableCell>
            <TableCell>{item.password}</TableCell>
            <TableCell>{item.firstName}</TableCell>
            <TableCell>{item.lastName}</TableCell>
            <TableCell>{item.personalId}</TableCell>
            <TableCell>{item.debt}</TableCell>
            <TableCell>{item.age}</TableCell>
            <TableCell>{active ? 'True' : 'False'}</TableCell>
            <TableCell>
            <Button onClick={() =>
                    handleUserDelete(item.id)}>
                    Delete User
                </Button>
            </TableCell>
        </TableRow>
        </>
    );
}

export default UsersRow