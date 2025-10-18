import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { getActivityDetail } from '../services/api';
import { Box, Card, CardContent, Divider, Typography } from '@mui/material';

const ActivityDetail = () => {
  const { id } = useParams();
  const [activity, setActivity] = useState(null);

  useEffect(() => {
    const fetchActivityDetail = async () => {
      try {
        const response = await getActivityDetail(id);
        setActivity(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchActivityDetail();
  }, [id]);

  if (!activity) {
    return <Typography>Generating AI recommendations...</Typography>;
  }

  return (
    <Box sx={{ maxWidth: 800, mx: 'auto', p: 2 }}>
      {/* Activity Info */}
      <Card sx={{ mb: 2 }}>
        <CardContent>
          <Typography variant="h5" gutterBottom>Activity Details</Typography>
          <Typography>Date: {new Date(activity.createdAt).toLocaleString()}</Typography>
        </CardContent>
      </Card>

      {/* AI Recommendation */}
      {activity.recommendation && (
        <Card>
          <CardContent>
            <Typography variant="h5" gutterBottom>AI Recommendation</Typography>

            <Typography variant="h6" gutterBottom>Analysis</Typography>
            <Typography paragraph>{activity.recommendation}</Typography>

            {/* Improvements */}
            {activity.improvements?.length > 0 && (
              <>
                <Divider sx={{ my: 2 }} />
                <Typography variant="h6" gutterBottom>Improvements</Typography>
                {activity.improvements.map((improvement, index) => (
                  <Typography key={index} paragraph>• {improvement}</Typography>
                ))}
              </>
            )}

            {/* Suggestions */}
            {activity.suggestions?.length > 0 && (
              <>
                <Divider sx={{ my: 2 }} />
                <Typography variant="h6" gutterBottom>Suggestions</Typography>
                {activity.suggestions.map((suggestion, index) => (
                  <Typography key={index} paragraph>• {suggestion}</Typography>
                ))}
              </>
            )}

            {/* Safety Guidelines */}
            {activity.safety?.length > 0 && (
              <>
                <Divider sx={{ my: 2 }} />
                <Typography variant="h6" gutterBottom>Safety Guidelines</Typography>
                {activity.safety.map((safety, index) => (
                  <Typography key={index} paragraph>• {safety}</Typography>
                ))}
              </>
            )}
          </CardContent>
        </Card>
      )}
    </Box>
  );
};

export default ActivityDetail;
