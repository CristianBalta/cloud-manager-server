echo "Building..."
gradle clean build -Pspring.profiles.active=gcp
echo "Building Docker image..."
docker build -t eu.gcr.io/bachelor-degree-318214/cloud-manager-server:latest .
echo "Pushing Docker image..."
docker push eu.gcr.io/bachelor-degree-318214/cloud-manager-server:latest
echo "Deploying Docker image..."
gcloud run deploy cloud-manager-server --platform managed --region europe-west4 --project bachelor-degree-318214 --image eu.gcr.io/bachelor-degree-318214/cloud-manager-server:latest
echo "Migrate traffic..."
gcloud run services --platform managed --region europe-west4 update-traffic cloud-manager-server --to-latest
