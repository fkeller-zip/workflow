const express = require('express');
const axios = require('axios');
const app = express();
const port = 8080;

app.use(express.json());

let events = [];

app.post('/v1/dataset', (req, res) => {
  events = req.body.events;
  res.status(200).send('Events received');
});

const calculateUsage = () => {
  const usage = events.reduce((acc, event) => {
    if (event.eventType === 'start') {
      acc[event.customerId] = (acc[event.customerId] || 0) - event.timestamp;
    } else if (event.eventType === 'stop') {
      acc[event.customerId] = (acc[event.customerId] || 0) + event.timestamp;
    }
    return acc;
  }, {});

  const result = Object.keys(usage).map(customerId => ({
    customerId,
    consumption: usage[customerId]
  }));

  return result;
};

app.post('/v1/result', async (req, res) => {
  const result = calculateUsage();

  try {
    await axios.post('http://reference-system.local/v1/result', { result });
    res.status(200).send('Results sent successfully');
  } catch (error) {
    res.status(500).send('Error sending results');
  }
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
