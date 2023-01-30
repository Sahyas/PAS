import {Button, TextField} from '@mui/material';
import React from "react";

export const AddBookForm = ({
                                    handleSubmit,
                                    setTitle,
                                    setAuthor,
                                    setSerialNuber,
                                    setGenre
                                }) => (
    <form className="form-container">
          Add new book:
        <br /><br />
        <label>Book title:</label>
        <br />
        <TextField
            sx={{
                width: { sm: 500, md: 500, margin: 5},
                "& .MuiInputBase-root": {
                    height: 50}
            }}
            onChange={(e) =>
            setTitle(e.target.value)} />
        <br />
        <label>Book author:</label>
        <br />
        <TextField
            sx={{
                width: { sm: 500, md: 500, margin: 5},
                "& .MuiInputBase-root": {
                    height: 50}
            }}
            onChange={(e) =>
            setAuthor(e.target.value)} />
        <br />
        <label>Book serial number:</label>
        <br />
        <TextField
            sx={{
                width: { sm: 500, md: 500, margin: 5},
                "& .MuiInputBase-root": {
                    height: 50}
            }}
            onChange={(e) =>
            setSerialNuber(e.target.value)} /> 
        <br />
        <label>Book genre:</label>
        <br />
        <TextField
            sx={{
                width: { sm: 500, md: 500, margin: 5},
                "& .MuiInputBase-root": {
                    height: 50}
            }}
            onChange={(e) =>
            setGenre(e.target.value)} />           
        
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