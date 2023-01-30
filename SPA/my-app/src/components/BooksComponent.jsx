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
import { Link } from "react-router-dom";
import BooksRow from "./BooksRow";
import {fetcher} from "../utils";

function Books() {

    const { data, error, isLoading } = useSWR(
        `${process.env.REACT_APP_URL}/books`,
        fetcher
    );

    console.log(data);

    return (
        <Box>
            <Table sx={{ margin: 5 }} size="large">
                <TableHead>
                    <TableRow>
                        <TableCell>BookId</TableCell>
                        <TableCell>Title</TableCell>
                        <TableCell>Author</TableCell>
                        <TableCell>SerialNumber</TableCell>
                        <TableCell>Genre</TableCell>
                        <TableCell>Rented</TableCell>
                        <TableCell>Delete</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data?.map((item) => (
                        <BooksRow key={item.id} item={item} />
                    ))}
                </TableBody>
            </Table>
            <Link to="/books/add">
                <Button 
                variant="contained" 
                sx={{ mt: 2, mb: 2, margin: 5 }} 
                color="success"
                >
                    Add Book
                </Button>
            </Link>
        </Box>
    );
}

export default Books;