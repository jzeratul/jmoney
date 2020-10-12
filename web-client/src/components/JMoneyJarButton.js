import React from 'react';
import { Button } from 'react-bootstrap';

const JMoneyJarButton = props => {

  const buttonClicked = () => {
    props.onClickHandler(props.jar);
  };

  return (
    <Button
      variant={props.jar.variant}
      name={props.jar.name}
      onClick={buttonClicked}
    >
        {props.jar.name} € {props.jar.money}
    </Button>
  );
};

export default JMoneyJarButton;
