<?php

namespace App\Controller;

use App\Entity\Region;
use App\Form\RegionType;
use App\Repository\RegionRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;


/**
 * @Route("/region")
 */
class RegionController extends AbstractController
{
    /**
     * @Route("/", name="app_region_index", methods={"GET"})
     */
    public function index(Request $request ,RegionRepository $regionRepository,PaginatorInterface $paginator): Response
    {
        $alltype=$regionRepository->findAll();
        // Paginate the results of the query
        $regionRepository = $paginator->paginate(
        // Doctrine Query, not results
            $alltype,
            // Define the page parameter
            $request->query->getInt('page', 1),
            // Items per page
            2
        );
        return $this->render('region/index.html.twig', [
            'regions' => $regionRepository,
        ]);
    }

    /**
     * @Route("/new", name="app_region_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager ): Response
    {
        $region = new Region();
        $form = $this->createForm(RegionType::class, $region);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('photo')->getData();
            if ($file != null){
                $fileName = md5(uniqid()).'.'.$file->guessExtension();
                $file->move($this->getParameter('upload_directory'),$fileName);
                $region->setPhoto($fileName);
            }
            $entityManager->persist($region);
            $entityManager->flush();

            return $this->redirectToRoute('app_region_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('region/new.html.twig', [
            'region' => $region,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_region_show", methods={"GET"})
     */
    public function show(Region $region): Response
    {
        return $this->render('region/show.html.twig', [
            'region' => $region,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_region_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Region $region, RegionRepository $regionRepository): Response
    {
        $form = $this->createForm(RegionType::class, $region);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $regionRepository->add($region);
            return $this->redirectToRoute('app_region_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('region/edit.html.twig', [
            'region' => $region,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_region_delete", methods={"POST"})
     */
    public function delete(Request $request, Region $region, RegionRepository $regionRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$region->getId(), $request->request->get('_token'))) {
            $regionRepository->remove($region);
        }

        return $this->redirectToRoute('app_region_index', [], Response::HTTP_SEE_OTHER);
    }

    /**
     * @Route("/Region/ajax_search", name="ajax_search" ,methods={"GET"})
     * @param Request $request
     * @param RegionRepository $regionRepository
     * @return Response
     */
    public function searchAction(Request $request,RegionRepository $regionRepository) : Response
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $regions =$regionRepository->SearchNom($requestString);
        if(!$regions) {
            $result['regions']['error'] = "region non trouvée ";
        } else {
            $result['regions'] = $this->getRealEntities($regions);
        }
        return new Response(json_encode($result));
    }
    public function getRealEntities($regions){
        foreach ($regions as $region){
            $realEntities[$region->getId()] = [$region->getPhoto(),$region->getNom()];

        }
        return $realEntities;
    }


}
