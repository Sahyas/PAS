import React from "react";
import useSWR from "swr";
import {
    Table,
    TableBody,
    TableHead,
    TableCell,
    Box,
    TableRow,
    Button
} from "@mui/material"
import UsersRow from "./UsersRow";
import {fetcher} from "../utils";
import { Link } from "react-router-dom";

function Users() {

    const { data, error, isLoading } = useSWR(
        `${process.env.REACT_APP_URL}/users`,
        fetcher
    );

    console.log(data);

    return (
        <Box>
            <Table sx={{ margin: 5 }} size="large">
                <TableHead>
                    <TableRow>
                        <TableCell>UserId</TableCell>
                        <TableCell>Login</TableCell>
                        <TableCell>Password</TableCell>
                        <TableCell>First Name</TableCell>
                        <TableCell>Last Name</TableCell>
                        <TableCell>Personal Id</TableCell>
                        <TableCell>Debt</TableCell>
                        <TableCell>Age</TableCell>
                        <TableCell>isActive</TableCell>
                        <TableCell>Delete</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data?.map((item) => (
                        <UsersRow key={item.id} item={item} />
                    ))}
                </TableBody>
            </Table>
            <Link to="/users/add">
                <Button 
                variant="contained" 
                sx={{ mt: 2, mb: 2, margin: 5 }} 
                color="success"
                >
                    Add User
                </Button>
            </Link>
        </Box>
    );
}

export default Users;