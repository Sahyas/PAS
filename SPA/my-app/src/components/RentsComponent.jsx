import React from "react";

import useSWR from "swr";
import {
    Table,
    TableBody,
    TableHead,
    TableCell,
    Box,
    TableRow
} from "@mui/material"
import RentsRow from "./RentsRow";
import {fetcher} from "../utils";

function Rents() {
    
    const { data, error, isLoading } = useSWR(
        `${process.env.REACT_APP_URL}/rents`,
        fetcher
    );

    console.log({data});

    return (
        <Box>
            <Table sx={{ margin: 5 }} size="large">
                <TableHead>
                    <TableRow>
                        <TableCell>RentId</TableCell>
                        <TableCell>Begin Time</TableCell>
                        <TableCell>End Time</TableCell>
                        <TableCell>Book</TableCell>
                        <TableCell>User</TableCell>
                        <TableCell>Manage Rent</TableCell>
                        <TableCell>Delete Rent</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data?.map((item) => (
                        <RentsRow key={item.id} item={item} />
                    ))}
                </TableBody>
            </Table>
        </Box>
    );
}

export default Rents;