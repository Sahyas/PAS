import {Button, TextField} from '@mui/material';
import React from "react";

export const AddUserForm = ({
                                    handleSubmit,
                                    setFirstName,
                                    setLastName,
                                    setLogin,
                                    setPassword,
                                    setPersonalId,
                                    setAge
                                }) => (
    <form className="form-container">
          Add new user:
        <br /><br />
        <label>First name:</label>
        <br />
        <TextField
            sx={{
                width: { sm: 500, md: 500, margin: 5},
                "& .MuiInputBase-root": {
                    height: 50}
            }}
            onChange={(e) =>
            setFirstName(e.target.value)} />
        <br />
        <label>Last name:</label>
        <br />
        <TextField
            sx={{
                width: { sm: 500, md: 500, margin: 5},
                "& .MuiInputBase-root": {
                    height: 50}
            }}
            onChange={(e) =>
            setLastName(e.target.value)} />
        <br />
        <label>Login:</label>
        <br />
        <TextField
            sx={{
                width: { sm: 500, md: 500, margin: 5},
                "& .MuiInputBase-root": {
                    height: 50}
            }}
            onChange={(e) =>
            setLogin(e.target.value)} /> 
        <br />
        <label>Password:</label>
        <br />
        <TextField
            sx={{
                width: { sm: 500, md: 500, margin: 5},
                "& .MuiInputBase-root": {
                    height: 50}
            }}
            onChange={(e) =>
            setPassword(e.target.value)} /> 
        <br />
        <label>Personal ID:</label>
        <br />
        <TextField
            sx={{
                width: { sm: 500, md: 500, margin: 5},
                "& .MuiInputBase-root": {
                    height: 50}
            }}
            onChange={(e) =>
            setPersonalId(e.target.value)} />                 
        <br />
        <label>Age:</label>
        <br />
        <TextField
            sx={{
                width: { sm: 500, md: 500, margin: 5},
                "& .MuiInputBase-root": {
                    height: 50}
            }}
            onChange={(e) =>
            setAge(e.target.value)} />       
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