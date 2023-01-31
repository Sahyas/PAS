import {
  Alert,
  AlertTitle,
  Box,
  Button,
  TextField,
  Typography,
} from "@mui/material";
import axios from "axios";
import React, { useState } from "react";
import { Link } from "react-router-dom";


function Profile() {
    const [oldPassword, setOldPassword] = useState("");
    const [password, setPassword] = useState("");
    const [repeatedPassword, setRepeatedPassword] = useState("");
    const [isPasswordValid] = useState("Wrong password");
    const [errorMessage, setErrorMessage] = useState();

    console.log(localStorage);

    function handleLogout(event) {
        console.log(localStorage);
        localStorage.clear();
        console.log(localStorage);
    }

    function handlePasswordChange(event) {
      if (password !== repeatedPassword) {
          alert("Wrong passwords")
      } else {
          const passwordChange = {
              newPassword: password,
              oldPassword: oldPassword,
          };
          axios
          .patch(`/users/changePassword`, passwordChange)
          .then((res) => 
          //window.location.reload()  
          console.log(res.data))
          .catch((err) => {
            console.log(err);
            setErrorMessage(err.response.data);
          });
      }
  }

    return (
        <>
          <Box sx={{ margin: "0 auto", width: "30%", paddingTop: 2 }}>
            {errorMessage && (
              <Alert
                severity="error"
                onClose={() => {
                  setErrorMessage(null);
                }}
              >
                <AlertTitle>Error</AlertTitle>
                <strong>{errorMessage}</strong>
              </Alert>
            )}
            <Box sx={{ display: "flex", flexDirection: "column" }}>
              <Typography variant="h6" sx={{ padding: 2 }}>
                <b>Change password</b>
              </Typography>
              <TextField
                error={!isPasswordValid}
                type="password"
                label="Old password"
                required
                variant="outlined"
                onChange={(e) => setOldPassword(e.target.value)}
              ></TextField>
              <TextField
                sx={{ marginTop: "15px" }}
                type="password"
                error={!isPasswordValid}
                helperText={!isPasswordValid ? "Password is not valid" : " "}
                label="New password"
                required
                variant="outlined"
                onChange={(e) => setPassword(e.target.value)}
              ></TextField>
              <TextField
                sx={{ marginBottom: "15px" }}
                type="password"
                error={!isPasswordValid}
                helperText={!isPasswordValid ? "Password is not valid" : " "}
                label="Repeat new password"
                required
                variant="outlined"
                onChange={(e) => setRepeatedPassword(e.target.value)}
              ></TextField>
              <Button
                variant="contained"
                color="success"
                onClick={handlePasswordChange}
              >
                Change password
              </Button><br />
              <Link to="/login">
              <Button 
              color="success" 
              onClick={handleLogout}
              variant="contained">
                Log out
              </Button>
            </Link>
            </Box>
          </Box>
        </>
      );
}

export default Profile;