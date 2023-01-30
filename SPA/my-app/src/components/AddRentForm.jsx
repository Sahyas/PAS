import {Autocomplete, Button, Rating, TextField} from '@mui/material';
import React from "react";

export const AddRentForm = ({
                                    handleSubmit,
                                    setBookId,
                                    setClientId
                                }) => (
    <form className="form-container">
          Add new rent:
        <br /><br />
        <label>Book Id:</label>
        <br />
        <TextField
            sx={{
                width: { sm: 500, md: 500, margin: 5},
                "& .MuiInputBase-root": {
                    height: 50}
            }}
            onChange={(e) =>
            setBookId(e.target.value)} />
        <br />
        <label>clientId:</label>
        <br />
        <TextField
            sx={{
                width: { sm: 500, md: 500, margin: 5},
                "& .MuiInputBase-root": {
                    height: 50}
            }}
            onChange={(e) =>
            setClientId(e.target.value)} />
        <br /><br />
        <Button
            color="success"
            sx={{ width: '40', mt: 3, mb: 2, margin: 2 }}
            variant="contained"
            onClick={handleSubmit}
            className="search-btn"
            >
            Submit
        </Button>
    </form>
);