import React from "react";
import {
    Box,
    AppBar,
    Button,
    IconButton, Icon, Toolbar, Typography
} from "@mui/material";
import { Link } from "react-router-dom";

function Header() {

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static"
                    sx={{ bgcolor: "whitesmoke" }}>
                <Toolbar>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }} color="green">
                        Library
                    </Typography>
                    <Link to="/books">
                        <Button variant="contained" sx={{ mt: 3, mb: 2 }} color="success">
                            Books
                        </Button>
                    </Link>
                    <Box sx={{ flex: 1 }} />
                    <Link to="/users">
                        <Button variant="contained" sx={{ mt: 3, mb: 2 }} color="success">
                            Users
                        </Button>
                    </Link>
                    <Box sx={{ flex: 1 }} />
                    <Link to="/rents">
                        <Button variant="contained" sx={{ mt: 3, mb: 2 }} color="success">
                            Rents
                        </Button>
                    </Link>
                    <Box sx={{ flex: 1 }}
                    />
                    <Link to="/rents/add">
                        <Button variant="contained" sx={{ mt: 3, mb: 2 }} color="success">
                            Add Rent
                        </Button>
                    </Link>
                    <Box sx={{ flex: 1 }} />
                    <Box sx={{ flex: 1 }}
                    />
                    {localStorage.getItem("jwtToken") != undefined ? (
                        <Link to="/profile">
                            <Button variant="contained" 
                            sx={{ mt: 3, mb: 2 }} 
                            color="success">
                                Profile
                            </Button>
                        </Link>
                    ) : (
                        <Link to="/login">
                            <Button variant="contained" 
                            sx={{ mt: 3, mb: 2 }} 
                            color="success">
                                Profile
                            </Button>
                        </Link>
                    )}
                    <Box sx={{ flex: 1 }} />
                </Toolbar>
            </AppBar>
        </Box>
    );

}

export default Header;