import React, { useEffect, useState } from "react";
import './css.css';

const Toast = ({ title, body }) => {
  const [show, setShow] = useState(true);

  const handleClose = () => {
    setShow(false);
  };

  const handleOpen = () => {
    setShow(true);

    setTimeout(() => {
        setShow(false);
    }, 3000);
  };

  useEffect(() => {
    if(body) {
      handleOpen();
    } else {
      handleClose();
    }
  }, [body])

  return (
    <div
      className={`toast position-fixed top-0 end-0 my-5 mx-2 ${show ? "show" : ""}`}
    >
      <div className="toast-header">
        <i className="bi bi-exclamation-circle text-warning"></i>
        <strong className="me-auto mx-3">{title}</strong>
        <button
          type="button"
          className="btn-close"
          data-bs-dismiss="toast"
          aria-label="Close"
          onClick={handleClose}
        ></button>
      </div>
      <div className="toast-body">{body}</div>
    </div>
  );
};

export default Toast;
