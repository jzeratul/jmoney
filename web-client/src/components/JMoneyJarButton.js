import React, { useState } from 'react';
import { Button } from 'react-bootstrap';

const JMoneyJarButton = props => {

  const [jar] = useState(props.jar);

  const buttonClicked = () => {
    props.onClickHandler(jar);
  };

  return (
    <Button
      variant={jar.variant}
      name={jar.name}
      onClick={buttonClicked}
    >
        {jar.name} € {jar.money}
    </Button>
  );
};

export default JMoneyJarButton;
