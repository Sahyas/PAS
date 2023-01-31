import { Button, Typography } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import { Link } from "react-router-dom";

function RentAdded() {
    return (
        <Box sx={{margin : '0 auto'}}>
        <Typography>
          Rent added successfully
        </Typography>
        <Link to="/rents">
          <Button 
          variant="contained" 
          sx={{ mt: 3, mb: 2 }}
          color="success">
            OK
          </Button>
        </Link>
      </Box> 
    )
}

export default RentAdded;