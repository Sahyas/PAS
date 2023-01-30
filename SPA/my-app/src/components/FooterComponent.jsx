import React from "react";
import {AppBar, Box, Button, Toolbar, Typography} from "@mui/material"
import {Link} from "react-router-dom";


function Footer() {
    return (
        <Box>
            <AppBar position="static"
                    sx={{ bgcolor: "whitesmoke" }}>
                <Toolbar>
                    <Typography 
                    variant="h6" 
                    component="footer" 
                    sx={{ flexGrow: 1,
                         bottom: 0,
                         minHeight: 0,
                         position: 'fixed' }} 
                         color="green">
                        Library Footer
                    </Typography>
                </Toolbar>
            </AppBar>
        </Box>
    )
}

export default Footer;