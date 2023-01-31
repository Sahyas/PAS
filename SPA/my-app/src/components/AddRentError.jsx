import { Button, Typography } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import { Link } from "react-router-dom";

function RentError() {
    return (
        <Box sx={{margin : '0 auto'}}>
        <Typography>
          Rent could not be added.
        </Typography>
        <Link to="/rents">
          <Button 
          variant="contained" 
          sx={{ mt: 3, mb: 2 }}
          color="error">
            OK
          </Button>
        </Link>
      </Box> 
    )
}

export default RentError;